package dev.jcasben.noticeboardservice;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@WebService(serviceName = "noticeBoard")
public class NoticeBoardServiceImpl implements NoticeBoardService {
    private final Map<String, Notice> noticeMap = new ConcurrentHashMap<>();

    @WebMethod
    public String createNotice(String message, int minutesValid) {
        String code = UUID.randomUUID().toString();
        LocalDateTime expiration = LocalDateTime.now().plusMinutes(minutesValid);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatedExpiration = expiration.format(formatter);

        noticeMap.put(code, new Notice(message, expiration, code, formatedExpiration));

        return code;
    }

    @WebMethod
    public boolean deleteNotice(String code) {
        return noticeMap.remove(code) != null;
    }

    @WebMethod
    public List<Notice> getAllNotices() {
        List<Notice> list = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Notice notice : noticeMap.values()) {
            if (notice.getExpiration().isAfter(now)) list.add(notice);
        }

        return list;
    }
}
