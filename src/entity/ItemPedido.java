/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Escola
 */
public class ItemPedido {
    private Double valor;
    private int quantidade;
    private Produto produto;
    

    public ItemPedido(Double valor, int quantidade, Produto produto) {
        this.valor = valor;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public ItemPedido() {
        
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "ItemPedido{" + "valor=" + valor + ", quantidade=" + quantidade + ", produto=" + produto + '}';
    }

   
}
