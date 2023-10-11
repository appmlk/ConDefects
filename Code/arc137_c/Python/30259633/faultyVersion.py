N = int(input())
A = list(map(int,input().split()))
if A[N-1]-A[N-2] >=2:
    print("Alice")
    exit()
if N%2 == A[N-1]%2:
    print("Bob")
else:
    print("Alice")