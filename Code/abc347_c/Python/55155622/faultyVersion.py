import sys
import queue
import math

def init():
    sys.stdin = open('in.txt', "r")
    sys.stdout = open('out.txt', 'w')

input = sys.stdin.readline
# s = set()
# a = int(input())
# mp = dict()
# q = queue.Queue()

n, a, b = map(int, input().split())
d = list(map(int, input().split()))

for i in range(n):
    d[i] %= (a + b)
    if d[i] == 0:
        d[i] = a + b
d.sort()
if d[n - 1] - d[0] + 1 <= a:
    print('Yes')
    exit(0)
for i in range(1, n):
    if d[i] - d[i - 1] <= a and d[i - 1] <= a:
        print('Yes')
        exit(0)
print("No")
#提交前记得修改DEBUG参数
#字符串读入一定要加strip