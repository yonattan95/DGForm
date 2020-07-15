import {
  Column,
  Entity,
  PrimaryGeneratedColumn,
  Table,
} from 'typeorm';

@Entity()
export class User {
  constructor() {}
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  username: string;

  @Column()
  password: string;

  @Column()
  email: string;

  @Column()
  state: number;

  @Column({
    default:
      'https://lh3.googleusercontent.com/proxy/cYLoDLhzWKYq3YpWPFprXc4DvHi-_S6YmvNVPJjQBjdiXtLX0-ROPY_ElEjU3DDOoomadywJ_GGs2kIHG3BMGJHPnfD4FMQtmoEgbf0Uyu7BMzWxKsU',
  })
  image: string;
}
