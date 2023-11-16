CREATE TABLE section (
  id BIGINT NOT NULL,
   name VARCHAR(255) NULL,
   CONSTRAINT pk_section PRIMARY KEY (id)
);

CREATE TABLE theme (
  id BIGINT NOT NULL,
   section_id BIGINT NULL,
   name VARCHAR(255) NULL,
   CONSTRAINT pk_theme PRIMARY KEY (id)
);
ALTER TABLE theme ADD CONSTRAINT FK_THEME_ON_SECTION FOREIGN KEY (section_id) REFERENCES section (id) ON DELETE CASCADE;


CREATE TABLE question (
  id BIGINT NOT NULL,
   text VARCHAR(255) NULL,
   CONSTRAINT pk_question PRIMARY KEY (id)
);

CREATE TABLE answer (
  id BIGINT NOT NULL,
   CONSTRAINT pk_answer PRIMARY KEY (id)
);

CREATE TABLE question_answers (
  answers_id BIGINT NOT NULL,
   question_id BIGINT NOT NULL,
   CONSTRAINT pk_question_answers PRIMARY KEY (answers_id, question_id)
);
ALTER TABLE question_answers ADD CONSTRAINT fk_queans_on_answer FOREIGN KEY (answers_id) REFERENCES answer (id);
ALTER TABLE question_answers ADD CONSTRAINT fk_queans_on_question FOREIGN KEY (question_id) REFERENCES question (id);



CREATE TABLE test (
  id BIGINT NOT NULL,
   section_id BIGINT NULL,
   theme_id BIGINT NULL,
   question_id BIGINT NULL,
   CONSTRAINT pk_test PRIMARY KEY (id)
);
ALTER TABLE test ADD CONSTRAINT FK_TEST_ON_QUESTION FOREIGN KEY (question_id) REFERENCES question (id) ON DELETE CASCADE;
ALTER TABLE test ADD CONSTRAINT FK_TEST_ON_SECTION FOREIGN KEY (section_id) REFERENCES section (id) ON DELETE CASCADE;
ALTER TABLE test ADD CONSTRAINT FK_TEST_ON_THEME FOREIGN KEY (theme_id) REFERENCES theme (id) ON DELETE CASCADE;