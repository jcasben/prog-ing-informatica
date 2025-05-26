package dev.jcasben.noticeboardservice;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface NoticeBoardService {
    @WebMethod String createNotice(String message, int minutesValid);
    @WebMethod boolean deleteNotice(String code);
    @WebMethod List<Notice> getAllNotices();
}
