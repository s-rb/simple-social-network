package ru.list.surkovr.social_network_simple.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    //JsonView - позволяет получать поля в методах помеченных соответствующей аннотацией
    @JsonView(Views.IdName.class)
    private String text;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullMessage.class)
    private LocalDateTime creationDate;

    @JsonView(Views.FullMessage.class)
    private String link;
    @JsonView(Views.FullMessage.class)
    private String linkTitle;
    @JsonView(Views.FullMessage.class)
    private String linkDescription;
    @JsonView(Views.FullMessage.class)
    private String linkCover;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.FullMessage.class)
    private User author;

    // orphanRemoval = true - если удаляется сообщение, то все комменты также удаляются
    @OneToMany(mappedBy = "message", orphanRemoval = true)
    @JsonView(Views.FullMessage.class)
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getLinkDescription() {
        return linkDescription;
    }

    public void setLinkDescription(String linkDescription) {
        this.linkDescription = linkDescription;
    }

    public String getLinkCover() {
        return linkCover;
    }

    public void setLinkCover(String linkCover) {
        this.linkCover = linkCover;
    }
}
