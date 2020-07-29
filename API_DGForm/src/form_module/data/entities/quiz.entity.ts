import {
  Column,
  Entity,
  ManyToOne,
  PrimaryGeneratedColumn,
} from 'typeorm';
import { Form } from './form.entity';

@Entity()
export default class Quiz {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ default: 0 })
  state: number;

  @Column({ name: 'last_question_completed', default: 0 })
  lastQuestionCompleted: number;

  // @Column({ name: 'form_id' })
  @ManyToOne(type => Form)
  form: number;
}
