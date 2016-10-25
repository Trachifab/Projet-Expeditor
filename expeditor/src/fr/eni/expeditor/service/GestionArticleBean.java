package fr.eni.expeditor.service;

import fr.eni.expeditor.entity.Article;
import fr.eni.expeditor.entity.Collaborateur;

import java.util.List;

/**
 * Created by d1502doreyf on 25/10/2016.
 */
public class GestionArticleBean extends AbstractService{

    public List<Article> rechercherTous(){
        return (List<Article>) consulter(Collaborateur.class);
    }

    public Article  rechercherParIdentifiant(Article article){
        return getEntityManager().find(Article.class, article);
    }

    public Article  rechercherParIdentifiantExterne(Article article){
        return getEntityManager().find(Article.class, article.getIdExterne());
    }

}
