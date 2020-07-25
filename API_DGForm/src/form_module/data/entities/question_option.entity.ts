import {
  Column,
  Entity,
  ManyToOne,
  PrimaryGeneratedColumn,
} from 'typeorm';
import Question from './question.entity';

@Entity()
export default class QuestionOption {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  description: string;

  @Column()
  value: string;

  // @Column({ name: 'question_id' })
  @ManyToOne(type => Question)
  question: number;
}
