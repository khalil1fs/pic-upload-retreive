package fst.sir.com.service.impl;

import fst.sir.com.bean.Image;
import fst.sir.com.dao.ImageDao;
import fst.sir.com.service.facade.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private ImageDao imageDao;

    public ImageServiceImpl(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Override
    public int save(Image[] images) {
        Arrays.stream(images).forEach(this::save);
        return 1;
    }

    @Override
    public List<Image> findAll() {
        return imageDao.findAll();
    }


    @Override
    public String save(Image image) {
        if (findByName(image.getName()) != null) return "already exist";
        else {
            Image entity = imageDao.save(image);
            return "saved";
        }
    }

    @Override
    public int update(Image image) {
        return 0;
    }

    @Transactional
    @Override
    public void delete(Long id) {
         imageDao.deleteById(id);
    }



    @Override
    public Image findByName(String name) {
        return imageDao.findByName(name);
    }
}
