import sys
input = sys.stdin.readline
def ip():return int(input())
def mp():return map(int, input().split())
def lmp():return list(map(int, input().split()))
# ABC251 D 1463 - At Most 3 (Contestant ver.)
W = ip()
A = []
for i in range(1, 101):
    A.append(i)
    A.append(100 * i)
    A.append(10000 * i)
print(300)
print(*A)
