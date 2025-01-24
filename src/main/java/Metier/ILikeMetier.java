/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Metier;

import MesBeans.Likes;

public interface ILikeMetier {
    String addLike(Likes like);
    Likes getLikeById(int id);
    String deleteLike(int id);
}
