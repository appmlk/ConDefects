N = int(input())
Arr = list(map(int, input().split()))

print(" ".join([str(n) for n in Arr if n % 2 == 0]))