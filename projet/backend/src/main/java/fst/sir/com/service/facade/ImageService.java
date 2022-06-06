package fst.sir.com.service.facade;


import fst.sir.com.bean.Image;

import java.util.List;

public interface ImageService {

//    String save(Image image);


    Image save(Image image);

    int save(Image[] images);

    int update(Image image);

    void delete(Long id);

    Image findByName(String name);

    List<Image> findAll();

}
