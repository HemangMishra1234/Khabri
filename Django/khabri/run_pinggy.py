import pinggy

# Set up the tunnel
tunnel = pinggy.create_tunnel("http", "127.0.0.1:8000")

# Start the tunnel
tunnel.start()

# Print the public URL
print(f"Public URL: {tunnel.public_url}")

# Keep the script running to maintain the tunnel
input("Press Enter to stop the tunnel...\n")
