N = int(input())

print(f"AGC{str(N+1).zfill(3)}" if N > 41 else f"AGC{str(N).zfill(3)}")