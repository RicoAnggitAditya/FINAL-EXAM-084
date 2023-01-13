/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_exam.final_exam;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @ RicoAnggitAditya
 * @ 20200140084
 * 
 */
@RestController
@CrossOrigin
@RequestMapping("/person")
public class myController {
    
    

    Person mydata = new Person();
    PersonJpaController ctrl = new PersonJpaController();

     // Untuk GET (menampilkan data)//
    @GetMapping("/{id}")
    public List<Person> getNameById(@PathVariable("id") int id) {
        List<Person> dummy = new ArrayList<>(); 
        try {
            mydata = ctrl.findPerson(id); 
            dummy.add(mydata); 
        } catch (Exception e) {
            dummy = List.of(); 
        }
        return dummy;
    }
    
   

    @GetMapping
    public List<Person> getAll() {
        List<Person> dummy = new ArrayList<>();
        try {
            dummy = ctrl.findPersonEntities(0, 0);
        } catch (Exception e) {
            dummy = List.of();
        }
        return dummy;
    }

    // Untuk POST (berfungsi untuk menambahkan data)//
    @PostMapping()
    public String createData(HttpEntity<String> paket) {
        String message = "";

        try {
            String json_receive = paket.getBody();

            ObjectMapper map = new ObjectMapper();

            Person data = new Person();

            data = map.readValue(json_receive, Person.class);

            ctrl.create(data);
            message = " Alhamdulillah data telah tersimpan";
            //data.getNamabarang() +

        } catch (Exception e) {
            message = "Coba Lagi";
        }

        return message;
    }

    // Untuk PUT (berfungsi untuk edit data)//
    @PutMapping()
    public String editData(HttpEntity<String> kiriman) {
        String message = "Tidak ada tindakan";
        try {
            String json_receive = kiriman.getBody();
            ObjectMapper mapper = new ObjectMapper();

            Person newdatas = new Person();

            newdatas = mapper.readValue(json_receive, Person.class);
            ctrl.edit(newdatas);
            message = "Data telah diperbaharui";
            //newdatas.getNamabarang() + 
        } catch (Exception e) {
        }
        return message;
    }

    // Untuk DELETE (berfungsi untuk menghapus data)//
    @DeleteMapping()
    public String deleteData(HttpEntity<String> kiriman) {
        String message = "Tidak ada tindakan";
        try {
            String json_receive = kiriman.getBody();
            ObjectMapper mapper = new ObjectMapper();

            Person newdatas = new Person();

            newdatas = mapper.readValue(json_receive, Person.class);
            ctrl.destroy(newdatas.getId());

            message = "Alhamdulillah Data sukses dihapus";
        } catch (Exception e) {
        }
        return message;
    }

}
