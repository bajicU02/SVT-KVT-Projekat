// src/app/models/gym.model.ts

import { WorkingTime } from './working-time.model';

export class Gym {
  id: number;
  name: string;
  city: string;
  address: string;
  description: string;
  disciplines: string[];
  ratings: number[];
  workingTimes: WorkingTime[] = [];

  constructor(
    id: number = 0,
    name: string = '',
    city: string = '',
    address: string = '',
    description: string = '',
    disciplines: string[] = [],
    ratings: number[] = [],
    workingTimes: WorkingTime[] = []
  ) {
    this.id = id;
    this.name = name;
    this.city = city;
    this.address = address;
    this.description = description;
    this.disciplines = disciplines;
    this.ratings = ratings;
    this.workingTimes = workingTimes;
  }
}
