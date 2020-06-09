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
            IdSelectedProduct: 0,
            amountSelectedProduct: 1,
            listNumberOfProducts: [],
            requests: []
        };
        this.amountCharge = this.state.availableProducts[0].number;
    };
    componentWillMount = () => {
        this.handleChangeProduct(0);
    }
    /*
        getRequests = () => {
            fetch("http://localhost:8080/v1/pedidos/cliente/1", {
                method: 'GET',
                headers: {
                    'Accept': 'application/json'
                },
            }).then((response) => response.json()
                .then((data) => {
                    this.setState({ requests: data })
                })
            ).catch(
                error => {
                    console.log(error)
                    this.setState({ result: "Erro ao consultar produtos!" })
                }
            );
        }
    
        deleteRequest = (idRequest) => {
            fetch("http://localhost:8080/v1/pedidos/" + idRequest, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            }).then(function (response) {
                if (!response.ok) {
                    console.log("Falha ao deletar pedido n°" + idRequest);
                }
            }).then(() => this.getRequests()
            ).catch(
                error => {
                    console.log(error)
                }
            );
        }*/
    handleChangeProduct = (event) => {
        let { listNumberOfProducts, availableProducts, amountSelectedProduct } = this.state
        let idx = null
        console.log(event);

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

    buyProduct = () => {
        let { requests, availableProducts, amountSelectedProduct, IdSelectedProduct } = this.state

        requests.push({
            name: availableProducts[IdSelectedProduct].name,
            numberOfProducts: amountSelectedProduct,
            amountValue: this.amountCharge
        })

        this.setState({ requests })
    }

    delProducts = (idx) => {
        let { requests } = this.state
        requests.splice(idx)
        this.setState({ requests })
    }

    render() {
        let { availableProducts, IdSelectedProduct, listNumberOfProducts, amountSelectedProduct, requests } = this.state

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

                                    <button className="button-add" onClick={this.buyProduct}>+</button>
                                </> : <p><b className="error">Não temos em estoque!</b></p>
                            }
                        </div>
                        <div className="command">
                            {requests.length > 0 ?
                                <>
                                    {requests.map((request, index) => {
                                        return (
                                            <div key={index} className="card">
                                                <div className="card-body">
                                                    <p className="w100 flex-space-between mb1">
                                                        <><b>Produto:</b> {request.name}</>
                                                        <><b>Quantidade:</b> {request.numberOfProducts}</>
                                                        <><b>Valor unitário:</b> R${request.amountValue}</>
                                                        <img className=" mt1" src={del} onClick={() => this.delProducts(index)} alt="logo-del" height="20px" width="20px" />
                                                    </p>
                                                </div>
                                            </div>
                                        );
                                    })}
                                </> : <p className="success mb5"><b>Nenhum pedido!</b></p>}

                            <button onClick={this.finishShopping}>FINALIZAR COMPRA</button>
                        </div>
                    </div>
                </header>
            </div>
        );

    }
}
export default Pedidos;