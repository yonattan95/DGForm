import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export default class QuestionOption {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  description: string;

  @Column()
  value: string;

  @Column({ name: 'question_id' })
  questionId: number;
}
