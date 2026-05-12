package com.example.starsgallery.service;

import com.example.starsgallery.beans.Star;
import com.example.starsgallery.dao.IDao;

import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {

    private List<Star> stars;
    private static StarService instance;

    private StarService() {
        stars = new ArrayList<>();
        seed();
    }

    public static StarService getInstance() {
        if (instance == null) {
            instance = new StarService();
        }
        return instance;
    }

    private void seed() {
        stars.add(new Star("Emma Watson", "https://commons.wikimedia.org/wiki/Special:Redirect/file/Emma_Watson_2017_%28cropped%29.jpg", 4.5f));
        stars.add(new Star("Tom Cruise", "https://commons.wikimedia.org/wiki/Special:Redirect/file/Tom_Cruise_by_Gage_Skidmore.jpg", 4.2f));
        stars.add(new Star("Scarlett Johansson", "https://commons.wikimedia.org/wiki/Special:Redirect/file/Scarlett_Johansson_by_Gage_Skidmore_2019.jpg", 4.7f));
        stars.add(new Star("Leonardo DiCaprio", "https://commons.wikimedia.org/wiki/Special:Redirect/file/Leonardo_Dicaprio_Cannes_2019.jpg", 4.8f));
        stars.add(new Star("Kate Bosworth", "https://commons.wikimedia.org/wiki/Special:Redirect/file/Kate_Bosworth_Deauville_2011.jpg", 3.5f));
        stars.add(new Star("George Clooney", "https://commons.wikimedia.org/wiki/Special:Redirect/file/George_Clooney_2016.jpg", 4.0f));
        stars.add(new Star("Michelle Rodriguez", "https://commons.wikimedia.org/wiki/Special:Redirect/file/Michelle_Rodriguez_Cannes_2018.jpg", 5.0f));
    }

    @Override
    public boolean create(Star o) {
        return stars.add(o);
    }

    @Override
    public boolean update(Star o) {
        for (Star s : stars) {
            if (s.getId() == o.getId()) {
                s.setName(o.getName());
                s.setImg(o.getImg());
                s.setRating(o.getRating());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Star o) {
        return stars.remove(o);
    }

    @Override
    public Star findById(int id) {
        for (Star s : stars) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    @Override
    public List<Star> findAll() {
        return stars;
    }
}
