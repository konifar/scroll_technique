package com.konifar.scroll_technique.utils;

import com.konifar.scroll_technique.models.pojo.Photo;

import java.util.ArrayList;
import java.util.List;

public class MockUtils {

    private static MockUtils instance;

    private MockUtils() {
    }

    public static MockUtils getInstance() {
        if (instance == null) {
            instance = new MockUtils();
        }
        return instance;
    }

    public List<Photo> getListPhotos() {
        List<Photo> photos = new ArrayList<Photo>();
        photos.add(new Photo("https://farm2.staticflickr.com/1079/901626554_8bc51ec160_z.jpg", "Cat Eyes", "This cat hangs around our office and is not camera shy like the other ones."));
        photos.add(new Photo("https://farm8.staticflickr.com/7160/6672148713_4e2ab31099_z.jpg", "Cat", "Cat Turkish Angora cat of my friend looking at the sunset"));
        photos.add(new Photo("https://farm9.staticflickr.com/8501/8300920648_d4a21bba59_z.jpg", "Norwegian cat", "Norwegian cat of my friend....it's such big and it has such lovely eyes!"));
        photos.add(new Photo("https://farm8.staticflickr.com/7210/6843831417_861d6996e8_z.jpg", "The Cat - If Looks could kill", "The Cat - If Looks could kill"));
        photos.add(new Photo("https://farm8.staticflickr.com/7519/15899553966_5debf88369_z.jpg", "Our cat", "Oh no the cat has turned into an alien :-) I did warn you no more photos hahahahaha"));
        photos.add(new Photo("https://farm4.staticflickr.com/3761/12360621583_b8a71bbf05_z.jpg", "Kitty cat", "This is our family cat Boo she is not very impressed"));
        photos.add(new Photo("https://farm9.staticflickr.com/8193/8092398950_ccb7b18078_z.jpg", "Super Cat", "Super Cat really is a super. cat. Fully recovered and fully settled in to the Monroe home."));
        photos.add(new Photo("https://farm9.staticflickr.com/8370/8563250378_eef01607de_z.jpg", "A Sweet Cat!", "I like when my cat look to me!"));
        photos.add(new Photo("https://farm4.staticflickr.com/3014/3100622156_3dfbb7e7b1_z.jpg", "Cat Collection", "Cat Collection...Cat Collection...Cat Collection..."));
        photos.add(new Photo("https://farm2.staticflickr.com/1262/1484248550_7f994cf84b_o.jpg", "Linda (Rita's cat)", "Linda is a gorgeous girl cat. Yesterday we went to dinner at a friend's house and I could take some pictures of Linda and Boris (2 of their 7 cats)."));
        return photos;
    }

    public Photo getHeaderPhoto() {
        return new Photo("https://farm3.staticflickr.com/2702/4206179149_b72ec240c8_z.jpg", "Persian Cat", "Z Wrocławia przywiozłem fotkę tego pięknego kota o imieniu Behemot. This beautiful cat called Behemot come from Wrocław (Poland)");
    }

}
