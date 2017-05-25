export interface NormalizedServiceModel {
  id: number;
  name: string;
  category: {
    id: number;
    name: string;
    order: number;
  }
}

export interface ServiceModel {
  id: number;
  name: string;
  order: number;
  category: ServiceCategoryModel[];
}

interface ServiceCategoryModel {
  id: number;
  name: string;
  checked?: boolean;
}
