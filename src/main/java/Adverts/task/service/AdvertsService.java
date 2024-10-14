package Adverts.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Adverts.task.model.Adverts;
import Adverts.task.repository.AdvertsDAO;

import java.util.List;

@Service
public class AdvertsService {

    private final AdvertsDAO advertsDAO;

    @Autowired
    public AdvertsService(AdvertsDAO advertsDAO) {
        this.advertsDAO = advertsDAO;
    }

    public List<Adverts> getAllAdverts(String sortBy) {
        return advertsDAO.getAllAdverts(sortBy);
    }

    public Adverts getAdvertById(int id) {
        return advertsDAO.getAdvertById(id);
    }

    public Adverts createAdvert(Adverts advert) {
        return advertsDAO.save(advert);
    }

    public Adverts updateAdvert(int id, Adverts advertDetails) {
        Adverts existingAdvert = advertsDAO.getAdvertById(id);
        if (existingAdvert != null) {
            existingAdvert.setTitle(advertDetails.getTitle());
            existingAdvert.setFuelType(advertDetails.getFuelType());
            existingAdvert.setPrice(advertDetails.getPrice());
            existingAdvert.setIsNew(advertDetails.isNew());
            existingAdvert.setMileage(advertDetails.getMileage());
            existingAdvert.setFirstRegistration(advertDetails.getFirstRegistration());
            existingAdvert.setUpdatedAt(advertDetails.getUpdatedAt());
            return advertsDAO.updateAdvert(existingAdvert);
        }
        return null;
    }

    public boolean deleteAdvert(int id) {
        Adverts advert = advertsDAO.getAdvertById(id);
        if (advert != null) {
            advertsDAO.delete(advert);
            return true;
        } else {
            return false;
        }
    }
}
