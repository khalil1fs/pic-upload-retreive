package fst.sir.com.dao;

import fst.sir.com.bean.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao extends JpaRepository<Image,Long> {

    Image findByName(String name);

    int deleteByName(String name);


}