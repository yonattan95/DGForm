import { Column, Entity, ManyToOne, PrimaryColumn } from 'typeorm';
import Question from './question.entity';
import Quiz from './quiz.entity';

@Entity()
export default class Answer {
  @PrimaryColumn()
  id: number;

  @Column()
  value: string;

  // @Column({ name: 'question_id' })
  @ManyToOne(type => Question)
  question: number;

  // @Column({ name: 'quiz_id' })
  @ManyToOne(type => Quiz)
  quiz: number;
}
