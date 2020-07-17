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
      'https://cdn2.iconfinder.com/data/icons/people-occupation-job/64/Ninja-Warrior-Assassin-Japan-Fighter-Avatar-Martial_arts-512.png',
  })
  image: string;
}
