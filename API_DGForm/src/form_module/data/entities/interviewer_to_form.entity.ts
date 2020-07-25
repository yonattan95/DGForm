import { Interviewer } from 'src/interviewer_module/data/entities/interviewer.entity';
import {
  Column,
  Entity,
  ManyToOne,
  PrimaryGeneratedColumn,
} from 'typeorm';
import { Form } from './form.entity';

@Entity()
export default class InterviewerToForm {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ name: 'start_date' })
  startDate: string;
  @Column({ name: 'end_date' })
  endDate: string;
  @Column({ name: 'quizzes_number_assigned' })
  quizzesNumberAssigned: number;

  @ManyToOne(
    type => Interviewer,
    interviewer => interviewer.interviewerToForm,
  )
  interviewer: number;

  @ManyToOne(
    type => Form,
    form => form.interviewerToForm,
  )
  form: number;
}
