import { Column, Entity, PrimaryColumn } from 'typeorm';

@Entity()
export default class Answer {
  @PrimaryColumn()
  id: number;

  @Column()
  value: string;

  @Column({ name: 'question_id' })
  questionId: number;
}