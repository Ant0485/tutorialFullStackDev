import { Component, Directive, EventEmitter, Input, Output, QueryList, ViewChildren, HostBinding, HostListener } from '@angular/core';

/** https://ng-bootstrap.github.io/#/components/table/examples#pagination */
  interface Employee {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    enabled: boolean;
  }

  /** Definition of a type who can be either '' or the types of Employee */
  export type SortColumn = keyof Employee | '';
  /** Definition of a type for the possible sorting directions */
  export type SortDirection = 'asc' | 'desc' | '';
  /** Object associating the next direction to take depending on the current one */
  const rotate: {[key: string]: SortDirection} = { 'asc': 'desc', 'desc': '', '': 'asc' };

  /** Comparison operation between number or string values */
  function compare (v1: string | number , v2: string | number) {
    v1 < v2 ? -1 : v1 > v2 ? 1 : 0;
  }

  function compare_booleans (v1: boolean, v2: boolean) {
    Number(v1) < Number(v2) ? -1 : Number(v1) > Number(v2) ? 1 : 0;
  }

  export interface SortEvent {
    column: SortColumn;
    direction: SortDirection;
  }

  @Directive({
    /** This directive is applied to all the html elements th who have a property "sortable" */
    selector: 'th[sortable]',
  })
  export class TableSortableHeader {
     @Input() sortable: SortColumn = '';
     @Input() direction: SortDirection | '';
     @Output() sort = new EventEmitter<SortEvent>();

     /** We bind the class property to either 'asc' or 'desc'. In the HTML, we will see either class="asc"
         or class="desc" depending on the value of direction. @Input("class") direction is implied. */
     @HostBinding('class.asc')
     get ascClass () {
      return this.direction === 'asc';
     }

     @HostBinding('class.desc')
     get descClass () {
      return this.direction === 'desc';
     }

    @HostListener('click', ['$event'])
    rotation() {
      this.direction = rotate[this.direction];
      this.sort.emit({column: this.sortable, direction: this.direction});
    }

  }

@Component({
  selector: 'app-employees-table',
  templateUrl: './employees-table.component.html',
  styleUrls: ['./employees-table.component.css']
})
export class EmployeesTableComponent  {
    @Input() employees: Array<any>;

    @ViewChildren(TableSortableHeader) headers: QueryList<TableSortableHeader>;

    /** The onSort method is called when we click on one of the column header, which procs the event
        that we have defines in the table header directive. */
    onSort({column, direction}: SortEvent) {

      // resetting other headers
      this.headers.forEach(header => {
        if (header.sortable !== column) {
          header.direction = '';
        }
      });

      if (direction != '' && column != '') {
        if (column != "enabled") {
          this.employees = this.employees.sort(
            (a,b) => {
            let res = a[column] < b[column] ? -1 : a[column] > b[column]  ? 1 : 0;
            return direction === 'asc' ? res : -res;
            });
        } else return direction === 'asc' ? res : -res;{
          this.employees = this.employees.sort(
            (a,b) => {
            let res = Number(a[column]) < Number(b[column]) ? -1 : Number(a[column]) > Number(b[column]) ? 1 : 0;
            return direction === 'asc' ? res : -res;
            });
        }

    }
   }

}


