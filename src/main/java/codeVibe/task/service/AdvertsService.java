package codeVibe.task.service;

import codeVibe.task.model.Adverts;
import codeVibe.task.repository.AdvertsDAO;

import java.util.List;

public class AdvertsService {

    private final AdvertsDAO advertsDAO;

    public AdvertsService(AdvertsDAO advertsDAO) {
        this.advertsDAO = advertsDAO;
    }

    public List<Adverts> getAllAdverts() {
        return advertsDAO.getAllAdverts();
    }

    public Adverts getAdvertById(int id) {
        return advertsDAO.getAdvertById(id);
    }

}
