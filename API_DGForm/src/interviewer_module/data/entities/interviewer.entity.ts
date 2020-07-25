import { Form } from 'src/form_module/data/entities/form.entity';
import InterviewerToForm from 'src/form_module/data/entities/interviewer_to_form.entity';
import { User } from 'src/user_module/data/entities/user.entity';
import {
  Column,
  Entity,
  ManyToMany,
  ManyToOne,
  OneToMany,
  PrimaryGeneratedColumn,
  Table,
} from 'typeorm';

@Entity()
export class Interviewer {
  constructor() {}
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  name: string;

  @Column()
  username: string;

  @Column({ name: 'surname_1' })
  surname1: string;

  @Column({ name: 'surname_2' })
  surname2: string;

  @Column()
  password: string;

  @Column()
  email: string;

  @Column({ default: 1 })
  state: number;

  @Column({
    default:
      'https://cdn2.iconfinder.com/data/icons/people-occupation-job/64/Ninja-Warrior-Assassin-Japan-Fighter-Avatar-Martial_arts-512.png',
  })
  image: string;

  // @Column({ name: 'user_id' })
  @ManyToOne(type => User)
  user: number;

  @OneToMany(
    type => InterviewerToForm,
    interviewerToForm => interviewerToForm.interviewer,
  )
  interviewerToForm!: InterviewerToForm[];
}
