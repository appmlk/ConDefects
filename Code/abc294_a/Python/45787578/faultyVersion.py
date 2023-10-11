N = int(input())
Arr = list(map(int, input().split()))

print([n for n in Arr if n % 2 == 0])