
package dev.jcasben.noticeboardclient2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dev.jcasben.noticeboardclient2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllNotices_QNAME = new QName("http://noticeboardservice.jcasben.dev/", "getAllNotices");
    private final static QName _DeleteNotice_QNAME = new QName("http://noticeboardservice.jcasben.dev/", "deleteNotice");
    private final static QName _CreateNoticeResponse_QNAME = new QName("http://noticeboardservice.jcasben.dev/", "createNoticeResponse");
    private final static QName _DeleteNoticeResponse_QNAME = new QName("http://noticeboardservice.jcasben.dev/", "deleteNoticeResponse");
    private final static QName _GetAllNoticesResponse_QNAME = new QName("http://noticeboardservice.jcasben.dev/", "getAllNoticesResponse");
    private final static QName _CreateNotice_QNAME = new QName("http://noticeboardservice.jcasben.dev/", "createNotice");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dev.jcasben.noticeboardclient2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllNoticesResponse }
     * 
     */
    public GetAllNoticesResponse createGetAllNoticesResponse() {
        return new GetAllNoticesResponse();
    }

    /**
     * Create an instance of {@link CreateNoticeResponse }
     * 
     */
    public CreateNoticeResponse createCreateNoticeResponse() {
        return new CreateNoticeResponse();
    }

    /**
     * Create an instance of {@link DeleteNoticeResponse }
     * 
     */
    public DeleteNoticeResponse createDeleteNoticeResponse() {
        return new DeleteNoticeResponse();
    }

    /**
     * Create an instance of {@link GetAllNotices }
     * 
     */
    public GetAllNotices createGetAllNotices() {
        return new GetAllNotices();
    }

    /**
     * Create an instance of {@link DeleteNotice }
     * 
     */
    public DeleteNotice createDeleteNotice() {
        return new DeleteNotice();
    }

    /**
     * Create an instance of {@link CreateNotice }
     * 
     */
    public CreateNotice createCreateNotice() {
        return new CreateNotice();
    }

    /**
     * Create an instance of {@link LocalDateTime }
     * 
     */
    public LocalDateTime createLocalDateTime() {
        return new LocalDateTime();
    }

    /**
     * Create an instance of {@link Notice }
     * 
     */
    public Notice createNotice() {
        return new Notice();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllNotices }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://noticeboardservice.jcasben.dev/", name = "getAllNotices")
    public JAXBElement<GetAllNotices> createGetAllNotices(GetAllNotices value) {
        return new JAXBElement<GetAllNotices>(_GetAllNotices_QNAME, GetAllNotices.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteNotice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://noticeboardservice.jcasben.dev/", name = "deleteNotice")
    public JAXBElement<DeleteNotice> createDeleteNotice(DeleteNotice value) {
        return new JAXBElement<DeleteNotice>(_DeleteNotice_QNAME, DeleteNotice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNoticeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://noticeboardservice.jcasben.dev/", name = "createNoticeResponse")
    public JAXBElement<CreateNoticeResponse> createCreateNoticeResponse(CreateNoticeResponse value) {
        return new JAXBElement<CreateNoticeResponse>(_CreateNoticeResponse_QNAME, CreateNoticeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteNoticeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://noticeboardservice.jcasben.dev/", name = "deleteNoticeResponse")
    public JAXBElement<DeleteNoticeResponse> createDeleteNoticeResponse(DeleteNoticeResponse value) {
        return new JAXBElement<DeleteNoticeResponse>(_DeleteNoticeResponse_QNAME, DeleteNoticeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllNoticesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://noticeboardservice.jcasben.dev/", name = "getAllNoticesResponse")
    public JAXBElement<GetAllNoticesResponse> createGetAllNoticesResponse(GetAllNoticesResponse value) {
        return new JAXBElement<GetAllNoticesResponse>(_GetAllNoticesResponse_QNAME, GetAllNoticesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNotice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://noticeboardservice.jcasben.dev/", name = "createNotice")
    public JAXBElement<CreateNotice> createCreateNotice(CreateNotice value) {
        return new JAXBElement<CreateNotice>(_CreateNotice_QNAME, CreateNotice.class, null, value);
    }

}
