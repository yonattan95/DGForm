import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

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

  @Column({ name: 'user_id' })
  userId: number;

  @Column({ name: 'category_id', default: 1 })
  categoryId: number;

  @Column({ name: 'start_date', default: new Date() })
  startDate: Date;

  @Column({ name: 'end_date' })
  endDate: Date;

  @Column({ name: 'all_quiz_assigned', default: 0 })
  allQuizAssigned: number;

  @Column({ default: new Date(), name: 'created_date' })
  createdDate: Date;
}
