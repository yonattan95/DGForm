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
      'https://cdn2.iconfinder.com/data/icons/people-occupation-job/64/Ninja-Warrior-Assassin-Japan-Fighter-Avatar-Martial_arts-512.png',
  })
  image: string;
}
