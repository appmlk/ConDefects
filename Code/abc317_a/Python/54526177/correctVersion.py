from bisect import bisect_left as bis
N, H, X = map(int, input().split())
P = tuple(map(int, input().split()))
print(bis(P, X-H) + 1)