N = int(input())
A = sorted(list(map(int, input().split())), reverse=True)
print('Alice' if A[0] > A[1] +
      1 else ('Alice' if (A[0] - N - 1) & 1 else 'Bob'))
