//===----------------------------------------------------------------------===//
//
//                         BusTub
//
// p0_starter.h
//
// Identification: src/include/primer/p0_starter.h
//
// Copyright (c) 2015-2020, Carnegie Mellon University Database Group
// Creating a new branch is quick AND simple.
//===----------------------------------------------------------------------===//
#pragma once

#include <memory>

namespace bustub {

/*
 * The base class defining a Matrix
 */
template <typename T>
class Matrix {
 protected:
  // TODO(P0): Add implementation
  Matrix(int r, int c) {
    linear = new T[r * c];
    rows = r;
    cols = c;
  }

  // # of rows in the matrix
  int rows;
  // # of Columns in the matrix
  int cols;
  // Flattened array containing the elements of the matrix
  // TODO(P0) : Allocate the array in the constructor. Don't forget to free up
  // the array in the destructor.
  T *linear;

 public:
  // Return the # of rows in the matrix
  virtual int GetRows() = 0;

  // Return the # of columns in the matrix
  virtual int GetColumns() = 0;

  // Return the (i,j)th  matrix element
  virtual T GetElem(int i, int j) = 0;

  // Sets the (i,j)th  matrix element to val
  virtual void SetElem(int i, int j, T val) = 0;

  // Sets the matrix elements based on the array arr
  virtual void MatImport(T *arr) = 0;

  // TODO(P0): Add implementation
  virtual ~Matrix() { delete[] linear; }
};

template <typename T>
class RowMatrix : public Matrix<T> {
 public:
  // TODO(P0): Add implementation
  RowMatrix(int r, int c) : Matrix<T>(r, c) {
    data_ = new T *[r];
    for (int i = 0; i < r; i++) {
      data_[i] = &(this->linear[i * c]);
    }
  }

  // TODO(P0): Add implementation
  int GetRows() override { return this->rows; }

  // TODO(P0): Add implementation
  int GetColumns() override { return this->cols; }

  // TODO(P0): Add implementation
  T GetElem(int i, int j) override { return data_[i][j]; }

  // TODO(P0): Add implementation
  void SetElem(int i, int j, T val) override { data_[i][j] = val; }

  // TODO(P0): Add implementation
  void MatImport(T *arr) override {
    for (int i = 0; i < (this->rows); i++) {
      for (int j = 0; j < this->cols; j++) {
        data_[i][j] = arr[i * (this->cols) + j];
      }
    }
  }

  // TODO(P0): Add implementation
  ~RowMatrix() override { delete[] data_; }

 private:
  T **data_;
};

template <typename T>
class RowMatrixOperations {
 public:
  // Compute (mat1 + mat2) and return the result.
  // Return nullptr if dimensions mismatch for input matrices.
  static std::unique_ptr<RowMatrix<T>> AddMatrices(
      std::unique_ptr<RowMatrix<T>> mat1, std::unique_ptr<RowMatrix<T>> mat2) {
    // TODO(P0): Add code
    if (mat1->GetRows() == mat2->GetRows() &&
        mat1->GetColumns() == mat2->GetColumns()) {
      int rows1 = mat1->GetRows();
      int cols1 = mat1->GetColumns();
      std::unique_ptr<RowMatrix<T>> ans(new RowMatrix<T>(rows1, cols1));
      for (int i = 0; i < rows1; i++) {
        for (int j = 0; j < cols1; j++) {
          ans->SetElem(i, j, mat1->GetElem(i, j) + mat2->GetElem(i, j));
        }
      }
      return ans;
    }
    return std::unique_ptr<RowMatrix<T>>(nullptr);
  }

  // Compute matrix multiplication (mat1 * mat2) and return the result.
  // Return nullptr if dimensions mismatch for input matrices.
  static std::unique_ptr<RowMatrix<T>> MultiplyMatrices(
      std::unique_ptr<RowMatrix<T>> mat1, std::unique_ptr<RowMatrix<T>> mat2) {
    // TODO(P0): Add code
    if (mat1->GetColumns() == mat2->GetRows()) {
      int r = mat1->GetRows();
      int c = mat2->GetColumns();
      int m = mat2->GetRows();
      std::unique_ptr<RowMatrix<T>> ret(new RowMatrix<T>(r, c));

      for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
          ret->SetElem(i, j, 0);
        }
      }

      for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
          for (int k = 0; k < m; k++) {
            ret->SetElem(
                i, j,
                ret->GetElem(i, j) + mat1->GetElem(i, k) * mat2->GetElem(k, j));
          }
        }
      }
      return ret;
    }
    return std::unique_ptr<RowMatrix<T>>(nullptr);
  }

  // Simplified GEMM (general matrix multiply) operation
  // Compute (matA * matB + matC). Return nullptr if dimensions mismatch for
  // input matrices
  static std::unique_ptr<RowMatrix<T>> GemmMatrices(
      std::unique_ptr<RowMatrix<T>> matA, std::unique_ptr<RowMatrix<T>> matB,
      std::unique_ptr<RowMatrix<T>> matC) {
    // TODO(P0): Add code
    auto temp = MultiplyMatrices(move(matA), move(matB));
    if (temp == nullptr) {
      return std::unique_ptr<RowMatrix<T>>(nullptr);
    }

    auto ans = AddMatrices(move(temp), move(matC));
    if (ans == nullptr) {
      return std::unique_ptr<RowMatrix<T>>(nullptr);
    }
    return ans;
  }
};
}  // namespace bustub
