package com.george.server.model.questions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.george.server.model.Theme;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Theme theme;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "question_answers",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "answers_id"))
    private Set<Answer> answers = new LinkedHashSet<>();

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
        answer.getQuestions().add(this);
    }

}
