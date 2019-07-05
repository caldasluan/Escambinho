package com.unb.devapp.escambinho.Model;

public class ItemModel {
    String id, userId, title, imageUrl, autor, editora, edicao, ano, paginas, condicao, descricao, tags;

    public ItemModel() {
        imageUrl = "";
    }

    public ItemModel(String id, String userId, String title, String imageUrl) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public ItemModel(String userId, String title, String imageUrl, String autor, String editora, String edicao, String ano, String paginas, String condicao, String descricao, String tags) {
        this.userId = userId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.autor = autor;
        this.editora = editora;
        this.edicao = edicao;
        this.ano = ano;
        this.paginas = paginas;
        this.condicao = condicao;
        this.descricao = descricao;
        this.tags = tags;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public String getEdicao() {
        return edicao;
    }

    public String getAno() {
        return ano;
    }

    public String getPaginas() {
        return paginas;
    }

    public String getCondicao() {
        return condicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTags() {
        return tags;
    }
}
