import { Column, Entity, PrimaryColumn } from 'typeorm';

@Entity()
export default class QuestionOption {
  @PrimaryColumn()
  id: number;

  @Column()
  name: string;

  @Column()
  value: string;

  @Column({ name: 'question_id' })
  questionId: number;
}