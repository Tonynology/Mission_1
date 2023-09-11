package com.example.mission_1;

import java.util.ArrayList;
import java.util.List;

public class BookMarkProcess {
    public List<BookMark> getBookMarkGrpList(){
        BookMarkService service = new BookMarkService();

        List<BookMark> bookMarkList = new ArrayList<>();
        bookMarkList = service.getBookMarkGrpList();

        return bookMarkList;
    }
    public BookMark getBookMarkGrpInfo(String id){
        BookMarkService service = new BookMarkService();
        return service.getBookMarkGrpInfo(id);
    }
    public boolean insertBookMarkGrp(BookMark bookMark) {
        BookMarkService service = new BookMarkService();
        return service.insertBookmarkGrp(bookMark);
    }

    public boolean updateBookMarkGrp(BookMark bookMark) {
        BookMarkService service = new BookMarkService();
        return service.updateBookmarkGrp(bookMark);
    }

    public boolean deleteBookMarkGrp(String id) {
        BookMarkService service = new BookMarkService();
        return service.deleteBookMarkWithBookMarkGroup(id) && service.deleteBookMarkGrp(id);
    }

    public boolean insertBookMark(BookMark bookMark) {
        BookMarkService service = new BookMarkService();
        return service.insertBookmark(bookMark);
    }

    public List<BookMark> getBookMarkList(){
        BookMarkService service = new BookMarkService();

        List<BookMark> bookMarkList = new ArrayList<>();
        bookMarkList = service.getBookMarkList();

        return bookMarkList;
    }

    public boolean deleteBookMark(String id) {
        BookMarkService service = new BookMarkService();
        return service.deleteBookMark(id);
    }
}
