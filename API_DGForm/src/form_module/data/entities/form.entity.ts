import Category from 'src/general_module/data/entities/category.entity';
import { Interviewer } from 'src/interviewer_module/data/entities/interviewer.entity';
import { User } from 'src/user_module/data/entities/user.entity';
import {
  Column,
  Entity,
  JoinTable,
  ManyToMany,
  ManyToOne,
  OneToMany,
  PrimaryGeneratedColumn,
} from 'typeorm';
import InterviewerToForm from './interviewer_to_form.entity';

enum FormStatusEnum {
  'pendiente' = 0,
  'completado' = 1,
}

@Entity()
export class Form {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  name: string;

  @Column()
  description: string;

  @Column({ default: 0 })
  state: FormStatusEnum;

  @Column({ name: 'start_date', default: new Date() })
  startDate: Date;

  @Column({ name: 'end_date' })
  endDate: Date;

  @Column({ name: 'all_quiz_assigned', default: 0 })
  allQuizAssigned: number;

  @Column({ default: new Date(), name: 'created_date' })
  createdDate: Date;

  // @Column({ name: 'user_id' })
  @ManyToOne(type => User)
  user: number;

  // @Column({ name: 'category_id', default: 2 })
  @ManyToOne(type => Category)
  category: number;

  @OneToMany(
    type => InterviewerToForm,
    interviewerToForm => interviewerToForm.form,
  )
  interviewerToForm!: InterviewerToForm[];
}
