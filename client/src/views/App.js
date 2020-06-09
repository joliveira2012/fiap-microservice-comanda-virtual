import React, { Component } from 'react';
import del from '../img/delete.svg';
import '../General.css';
import './App.css'

class Pedidos extends Component {
    constructor(props) {
        super(props);
        this.state = {
            availableProducts: [
                {
                    name: "Cerveja",
                    number: 25,
                    unitValue: 10.00
                },
                {
                    name: "Refrigerante",
                    number: 50,
                    unitValue: 5.00
                },
                {
                    name: "Whisky",
                    number: 0,
                    unitValue: 15.00
                },
            ],
            baseUrl: "http://localhost:8080/v1/comanda",
            showSuccessScreen: false,
            IdSelectedProduct: 0,
            amountSelectedProduct: 1,
            listNumberOfProducts: [],
            requests: [],
            commands: []
        };
        this.amountCharge = this.state.availableProducts[0].number;
        this.accCharge = 0;
    };

    componentWillMount = () => {
        this.handleChangeProduct(0);
    }

    getDate() {
        let date = new Date();
        var dateStr =
            date.getFullYear() + "-" +
            ("00" + (date.getMonth() + 1)).slice(-2) + "-" +
            ("00" + date.getDate()).slice(-2) + "T" +
            ("00" + date.getHours()).slice(-2) + ":" +
            ("00" + date.getMinutes()).slice(-2) + ":" +
            ("00" + date.getSeconds()).slice(-2);
        return dateStr;
    }

    openUserCommand = () => {
        let { requests } = this.state

        let products = [];
        requests.forEach((req, index) => {
            products[index] = {
                nome: req.name,
                quantidade: req.numberOfProducts,
                preco: req.amountValue
            }
        })

        let body = JSON.stringify({
            dataCompra: this.getDate(),
            produtos: products
        });

        fetch(this.state.baseUrl, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: body,
        }).then((response) => response.json()
            .then(() => {
                this.getCommands();
            })
        ).catch(
            error => {
                console.log(error)
                this.setState({ result: "Erro ao consultar produtos!" })
            }
        );
    }

    getCommands() {
        fetch(this.state.baseUrl, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            },
        }).then((response) => response.json()
            .then((commands) => {
                this.setState({ commands, showSuccessScreen: true })
            })
        ).catch(
            error => {
                console.log(error)
                this.setState({ result: "Erro ao consultar comandas!" })
            }
        );

    }


    finishUserCommand = (idCommand) => {
        fetch(this.state.baseUrl +"/"+ idCommand, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        }).then(function (response) {
            if (!response.ok) {
                console.log("Falha ao deletar a comanda n°" + idCommand);
            }
        }).then(() => this.getCommands()
        ).catch(
            error => {
                console.log(error)
            }
        );
    }

    handleChangeProduct = (event) => {
        let { listNumberOfProducts, availableProducts, amountSelectedProduct } = this.state
        let idx = null

        if (event !== 0)
            idx = event.target.value
        else
            idx = 0

        for (let i = 1; i <= availableProducts[idx].number; i++) {
            listNumberOfProducts.push(i);
        }

        this.amountCharge = this.state.availableProducts[idx].unitValue * amountSelectedProduct;
        this.setState({ IdSelectedProduct: idx, listNumberOfProducts })
    }

    handleChangeAmountProducts = (event) => {
        let numberSelectedProducts = event.target.value
        this.amountCharge = this.state.availableProducts[this.state.IdSelectedProduct].unitValue * numberSelectedProducts;
        this.setState({ amountSelectedProduct: numberSelectedProducts })
    }

    addProduct = () => {
        let { requests, availableProducts, amountSelectedProduct, IdSelectedProduct } = this.state

        requests.push({
            name: availableProducts[IdSelectedProduct].name,
            numberOfProducts: amountSelectedProduct,
            amountValue: this.amountCharge
        })
        if (requests.length > 1) {
            this.accCharge = requests.reduce(function (accumulator, currentValue) {
                return accumulator + currentValue.amountValue;
            }, 0)
            console.log(requests)
            console.log("accCharge")
            console.log(this.accCharge)
        } else {
            this.accCharge = this.amountCharge;
        }

        this.setState({ requests })
    }

    delProducts = (idx, productValue) => {
        let { requests } = this.state
        requests.splice(idx)
        this.accCharge = this.accCharge - productValue;
        this.setState({ requests })
    }

    getTotalValue (products) {
        return products.reduce(function (accumulator, currentValue) {
            return accumulator + currentValue.preco;
        }, 0)
    }

    render() {
        let { availableProducts,
            IdSelectedProduct,
            listNumberOfProducts,
            amountSelectedProduct,
            requests,
            commands,
            showSuccessScreen } = this.state

        if (showSuccessScreen) {
            return (
                <div className="App">
                    <header className="App-header">
                        <h1 className="success">Comanda Aberta</h1>
                        <div className="pedidos-container">
                            <div className="flex-center command">
                                {commands.map((command, index) => {
                                    return (
                                        <div key={index} className="card mr5">
                                            
                                            <p className="w100 flex-space-between"><b>A comanda {command.id} deve o valor: R${this.getTotalValue(command.produtos)}</b>
                                            <button className="button-add" onClick={() => this.finishUserCommand(command.id)}>PAGA!</button></p>
                                        </div>
                                    )
                                })}
                            </div>
                        </div>
                    </header>
                </div>
            );
        } else {
            return (
                <div className="App">
                    <header className="App-header">
                        <h1 className="success">Comanda</h1>
                        <div className="pedidos-container">
                            <div className="flex-space-between">
                                <p><b className="success">Escolha o produto:</b></p>
                                <select value={IdSelectedProduct} onChange={this.handleChangeProduct}>
                                    {availableProducts.map((product, index) => {
                                        return (
                                            <option key={index} value={index}>{product.name}</option>
                                        );
                                    })}
                                </select>

                                {availableProducts[IdSelectedProduct].number > 0 ?
                                    <>
                                        <p><b className="success">Escolha a quantidade:</b></p>
                                        <select value={amountSelectedProduct} onChange={this.handleChangeAmountProducts}>
                                            {listNumberOfProducts.map((amount, index) => {
                                                return (
                                                    <option key={index} value={amount}>{amount}</option>
                                                );
                                            })}
                                        </select>

                                        <p><b className="success">R$ {this.amountCharge}</b></p>

                                        <button className="button-add" onClick={this.addProduct}>+</button>
                                    </> : <p><b className="error">Não temos em estoque!</b></p>
                                }
                            </div>
                            <div className="command-item">
                                {requests.length > 0 ?
                                    <>
                                        {requests.map((request, index) => {
                                            return (
                                                <div key={index} className="card">
                                                    <div className="card-body">
                                                        <p className="w100 flex-space-between mb1">
                                                            <><b>Produto:</b> {request.name}</>
                                                            <><b>Qtde:</b> {request.numberOfProducts}</>
                                                            <><b>Valor:</b> R${request.amountValue}</>
                                                            <img className=" mt1" src={del} onClick={() => this.delProducts(index, request.amountValue)} alt="logo-del" height="20px" width="20px" />
                                                        </p>
                                                    </div>
                                                </div>
                                            );
                                        })}
                                        <div className="card-success">
                                            <div className="card-body">
                                                <p className="w100 flex-space-between mb1">
                                                    <><b>TOTAL:</b> {this.accCharge}</>
                                                </p>
                                            </div>
                                        </div>
                                        <button onClick={this.openUserCommand}>CRIAR COMANDA</button>
                                    </> : <p className="success mb5"><b>Nenhum pedido!</b></p>}
                            </div>
                        </div>
                    </header>
                </div>
            );
        }

    }
}
export default Pedidos;