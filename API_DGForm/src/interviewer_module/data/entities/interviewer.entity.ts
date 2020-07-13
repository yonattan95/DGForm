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
  username: string;

  @Column()
  password: string;

  @Column()
  email: string;

  @Column({default:1})
  state: number;
}
