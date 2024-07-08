import sys

inf = 1e9

def input():
    return sys.stdin.readline().strip()
    
def solution(chords):
    chords = [sorted(x) for x in chords]
    m = {e:s for s,e in chords}
    starts = set(map(lambda x: x[0], chords))
    ends = set(map(lambda x: x[1], chords))
    N = len(chords)
    s = []
    for x in range(N):
        if (x+1) in starts:
            s.append(x+1)
        elif (x+1) in ends:
            if s:
                e = s.pop()
                if e != m[x+1]:
                    print("Yes")
                    return
        
                
    print("No")
def main():
    n = int(input())
    solution(list([tuple(map(int, input().split())) for _ in range(n)]))
        
if __name__ == "__main__":
    main()