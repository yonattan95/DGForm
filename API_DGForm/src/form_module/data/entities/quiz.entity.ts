import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export default class Quiz {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ name: 'form_id' })
  formId: number;

  @Column({ default: 0 })
  state: number;

  @Column({ name: 'last_question_completed' })
  lastQuestionCompleted: number;
}
