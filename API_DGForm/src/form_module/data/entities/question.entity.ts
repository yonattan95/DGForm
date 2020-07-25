import QuestionType from 'src/general_module/data/entities/question_type.entity';
import {
  Column,
  Entity,
  ManyToOne,
  PrimaryGeneratedColumn,
} from 'typeorm';
import { Form } from './form.entity';

@Entity()
export default class Question {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  name: string;

  @Column({ name: 'question_number' })
  questionNumber: number;

  @Column()
  description: string;

  // @Column({ name: 'form_id' })
  @ManyToOne(type => Form)
  form: number;

  // @Column({ name: 'question_type_id' })
  @ManyToOne(type => QuestionType)
  questionType: number;
}
