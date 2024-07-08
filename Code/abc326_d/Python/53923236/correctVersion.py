import sys
def ii():return int(sys.stdin.readline().rstrip("\r\n"))
def mi():return map(int, sys.stdin.readline().rstrip("\r\n").split())
def lmi():return list(map(int, sys.stdin.readline().rstrip("\r\n").split()))
def line_list(n): return [sys.stdin.readline().rstrip("\r\n") for _ in range(n)]
def ii_list(n): return [ii()for _ in range(n)]
def lmi_list(n): return [lmi()for _ in range(n)]


N = ii()
R = input().rstrip("\r\n")
C = input().rstrip("\r\n")

def check_line(first_c, line):
    first_c_i = 0
    while first_c_i < N and line[first_c_i] == "": first_c_i += 1
    if first_c_i >= N - 2: return False
    if line[first_c_i] != first_c: return False
    A, B, C = False,False,False
    for i in range(first_c_i, N):
        c = line[i]
        if c == "A":
            if A: return False
            A = True
        if c == "B":
            if B: return False
            B = True
        if c == "C":
            if C: return False
            C = True
    return A and B and C


def check(m):
    # for h in range(N):
    #     if not check_line(R[h], m[h]): return False

    m = list(zip(*m))
    for w in range(N):
        if not check_line(C[w], m[w]): return False
    return True

def create_line_iter(h):
    first_c = R[h]
    c_list = ["A", "B", "C"]
    c_list = [c for c in c_list if c != first_c]
    for i1 in range(0, N - 3 + 1):
        for i2 in range(i1+1, N):
            for i3 in range(i1+1, N):
                if i2 == i3: continue
                ret_list = [""] * N
                ret_list[i1] = first_c
                ret_list[i2] = c_list[0]
                ret_list[i3] = c_list[1]
                yield ret_list

def mywork():
    from collections import deque
    dq = deque()
    dq.append((0, []))
    while len(dq) > 0:
        h, m = dq.pop()
        if h == N:
            if check(m):
                print("Yes")
                for line in m:
                    print("".join(["." if c == "" else c for c in line]))
                exit()
        else:
            for line in create_line_iter(h):
                dq.append((h+1, m + [line]))

mywork()
print("No")