s = input()
q = int(input())
def rec(t, k):
    if t == 0:
        return k
    elif k == 0:
        return ord(s[0]) - ord('A') + t
    elif k % 2 == 0:
        return rec(t-1, k//2) + 1
    else:
        return rec(t-1, k//2) + 2

def main():
    for _ in range(q):
        t, k = map(int, input().split())
        print(chr(ord('A') + rec(t, k-1)%3))
main()