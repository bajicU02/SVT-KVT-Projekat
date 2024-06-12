// src/app/models/working-time.model.ts

export class WorkingTime {
    dayOfWeek: string;
    openingTime: string;
    closingTime: string;
  
    constructor(dayOfWeek: string, openingTime: string, closingTime: string) {
      this.dayOfWeek = dayOfWeek;
      this.openingTime = openingTime;
      this.closingTime = closingTime;
    }
  }
  