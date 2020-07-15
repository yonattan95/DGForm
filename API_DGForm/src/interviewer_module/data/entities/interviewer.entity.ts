import {
  Column,
  Entity,
  PrimaryGeneratedColumn,
  Table,
} from 'typeorm';

@Entity()
export class Interviewer {
  constructor() {}
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ name: 'user_id' })
  userId: number;

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
      'https://lh3.googleusercontent.com/proxy/cYLoDLhzWKYq3YpWPFprXc4DvHi-_S6YmvNVPJjQBjdiXtLX0-ROPY_ElEjU3DDOoomadywJ_GGs2kIHG3BMGJHPnfD4FMQtmoEgbf0Uyu7BMzWxKsU',
  })
  image: string;
}
